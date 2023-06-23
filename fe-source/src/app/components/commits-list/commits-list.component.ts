import { Component, OnInit } from '@angular/core'
import { Commit } from 'src/app/models/commit.model'
import { CommitService } from 'src/app/services/commit.service'
import * as dayjs from 'dayjs'

@Component({
  selector: 'app-commits-list',
  templateUrl: './commits-list.component.html',
  styleUrls: ['./commits-list.component.css']
})
export class CommitsListComponent implements OnInit {

  commits: Commit[] = []
  commitsFiltered: Commit[] = []
  currentCommit= {}
  currentIndex = -1
  page = 1
  count = 0
  pageSize = 5
  pageSizes = [5, 10, 25, 50, 100]
  name = ''
  extension = ''

  constructor(private commitService: CommitService) {
  }

  ngOnInit(): void {
    this.retrieveCommits()
  }

  getRequestParams(searchName: string, page: number, pageSize: number): any {
    let params: any = {}
    if (searchName) {
      params[`name`] = searchName
    }
    if (page) {
      params[`page`] = page - 1
    }
    if (pageSize) {
      params[`size`] = pageSize
    }
    return params
  }

  retrieveCommits(): void {
    const params = this.getRequestParams(this.name, this.page, this.pageSize)
    this.commitService.getAll(params)
      .subscribe(response => {
          this.commits = response as Commit[]
          this.commitsFiltered = []
          this.count = response.length
          const currentDate = dayjs()
          this.commits.forEach((commit) => {
            let hours = currentDate.diff(dayjs(new Date(commit.commit.committer.date)), 'hours')
            let minutes = currentDate.diff(dayjs(new Date(commit.commit.committer.date)), 'minutes')
            const days = Math.floor(hours / 24)
            hours = hours - (days * 24)
            if (days === 0 && hours === 0) {
              commit.commit.committer.date = minutes + ' minutes ago'
            } else {
              commit.commit.committer.date = (days === 0 ? hours + ' hours ago' : days + ' days ago')
            }
            if (commit.commit.message.includes(this.name)) {
              this.commitsFiltered.push(commit);
            } else if (!this.name){
              this.commitsFiltered.push(commit);
            }
          })
        },
        error => {
          console.log(error)
        })
  }

  handlePageChange(event: number): void {
    this.page = event
    this.retrieveCommits()
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value
    this.page = 1
    this.retrieveCommits()
  }

  refreshList(): void {
    this.retrieveCommits()
    this.currentCommit = {}
    this.currentIndex = -1
  }

  setActiveCommit(commit: Commit, index: number): void {
    this.currentCommit = commit
    this.currentIndex = index
  }

  searchName(): void {
    this.page = 1
    this.retrieveCommits()
  }
}
