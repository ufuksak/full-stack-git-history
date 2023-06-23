#!/bin/bash
 
# This script create a new repo on github.com, then pushes the local repo from the current directory to the new remote.

# It is a fork of https://gist.github.com/robwierzbowski/5430952/.  Some of Rob's lines just didn't work for me, and to fix them I needed to make it more verbose so that a mere electrical engineer could understand it.

# This script gets a username from .gitconfig.  If it indicates that your default username is an empty string, you can set it with
# git config --add github.user YOUR_GIT_USERNAME

rm -rf .git
git init
git config --add github.user squarepeghires
git config user.name "SquarePegHires"
git config user.email "accounts@squarepeghires.com"
git add --all
git commit -m "Initial commit"

# Gather constant vars
CURRENTDIR=${PWD##*/}
USERNAME=$(git config github.user)
 
# Get user input
echo "Enter $USERNAME password"
read PASSWORD
echo "Enter collaborator username"
read COLLABORATORUSERNAME
echo "Name for new repo will be $CURRENTDIR-$COLLABORATORUSERNAME"

REPONAME=${CURRENTDIR}-${COLLABORATORUSERNAME}

echo "Will create a new *private* repo named $REPONAME"
echo "on github.com in user account $USERNAME."

# Curl some json to the github API oh damn we so fancy
curl -u $USERNAME:$PASSWORD https://api.github.com/user/repos -d "{\"name\": \"$REPONAME\", \"description\": \"\", \"private\": true, \"has_issues\": false, \"has_wiki\": false, \"has_projects\": false}"
 
# Set the freshly created repo to the origin and push
# You'll need to have added your public key to your github account
git remote add origin https://github.com/$USERNAME/$REPONAME.git
git push -u origin master

# Protect master
curl -u $USERNAME:$PASSWORD https://api.github.com/repos/$USERNAME/$REPONAME/branches/master/protection -d "{\"enforce_admins\": null, \"restrictions\": null, \"required_status_checks\": null, \"required_pull_request_reviews\":{\"require_code_owner_reviews\": true}}" -X PUT

# Add collaborator
curl -u $USERNAME:$PASSWORD https://api.github.com/repos/$USERNAME/$REPONAME/collaborators/$COLLABORATORUSERNAME -d "" -X PUT

# Subscribe to notifications
curl -u $USERNAME:$PASSWORD https://api.github.com/repos/$USERNAME/$REPONAME/subscription -d "{\"subscribed\": true}" -X PUT
