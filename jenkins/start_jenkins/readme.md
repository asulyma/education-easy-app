# Tutorial
### How to set up Jenkins:

1. Go to [Jenkins local URL](http://localhost:8090)
2. Inside of your mount directory you will need to find `initialAdminPassword` and enter it.
3. Install the following plugins (see below).
4. Put name and pass of first user.
5. Go to `Settings -> Configure System -> Locale` and put `EN` as Default Language.
6. Go to `Settings -> Manage Credentials` and create `git_user` and `docker_hub` creds.

### How to set up Build Pipeline:
1. Create new pipeline item with the following required staff:
  - Enable `This project is parameterized`, select `Git Parameter` with `BRANCH` name;
  - Define pipeline script from SCM with `Branch Specifier` as `${BRANCH}`
  - Enter `Script Path` as `jenkins/build_pipeline/Jenkinsfile`
  - Click `Save` and `Build with Parameters`

### Required plugins:
* Build Features
  - Build Timeout
  - Credentials Binding
  - SSH Agent
  - Timestamper
  - Workspace Cleanup

* Build Tools
  - Gradle (todo?)
  
* Build Analysis and Reporting
  - JUnit
  
* Pipelines and Continuous Delivery
  - Pipeline
  - GitHub Branch Source
  - Pipeline: GitHub Groovy Libraries
  - Pipeline: Stage View
  
* Source Code Management
  - Git
  - Git Parameter
  - GitHub

* Distributed Builds
  - SSH Build Agents
  
* User Management and Security
  - Matrix Authorization Strategy
  - PAM Authentication
  - LDAP
  
* Notifications and Publishing
  - SSH
  
* Languages
  - Locale
  
  
// todo testing:
Image Tag Parameter
Email Extension
Ansible
AnsiColor