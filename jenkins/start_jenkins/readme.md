# Jenkins Tutorial
***
## How to set up Jenkins at the first time:

1. Go to [Jenkins local URL](http://localhost:8090)
2. Inside of your mount directory you will need to find `initialAdminPassword` and enter it.
3. Install the following plugins (see below).
4. Put name and pass of first user.
5. Go to `Manage Jenkins -> Configure System -> Locale` and put `EN` as Default Language (mark additional cross too).
6. Go to `Manage Jenkins -> Manage Credentials` and create `github_user` and `dockerhub_user` credentials.
7. Go to `Manage Jenkins -> Manage Plugins` and install additional required plugins.

<br>
<details><summary><b>Required plugins</b></summary>
<br>

* **Organization and Administration:** Folders; OWASP Markup Formatter.
* **Build Features:** Build Timeout; Credentials Binding; Timestamper; Workspace Cleanup.
* **Build Analysis and Reporting:** JUnit.
* **Pipelines and Continuous Delivery:** Pipeline; GitHub Branch Source; Pipeline: Stage View.
* **Source Code Management:** Git; Git Parameter; GitHub.
* **User Management and Security:** Role-based Authorization Strategy.
* **Notifications and Publishing:** SSH.
* **Languages:** Locale.

**Second step of installing important plugins:** Image Tag Parameter; Ansible; AnsiColor.

</details>

***
#### How to set up Build Pipeline:

Create a job with Pipeline type and made the following required staff:
* Enable **This project is parameterized**
  * Select **Git Parameter** with `BRANCH` name and **Branch** parameter type;
* Define pipeline script from SCM
  * Enter GitHub repository url and credentials
  * Enter `${BRANCH}` value to **Branch Specifier** 
  * Enter **Script Path** as `jenkins/build_pipeline/Jenkinsfile`
  * Disable **Lightweight checkout**
* Your pipeline is ready to work

***
#### How to set up Deploy Pipeline:

Create a job with Pipeline type and made the following required staff:
* Enable **This project is parameterized**
  * Select **Choice Parameter** with `ENVIRONMENT` name and [`all`, `prod`, `stage`] choices (each with new line);
  * Select **Git Parameter** with `BRANCH` name and **Branch** parameter type;
  * Select **Image Tag Parameter** with `APP_DOCKER_IMAGE` name and `allsul/education_app` image name (also add access to dockerhub via **Advanced**);
  * Select **Image Tag Parameter** with `AUTH_DOCKER_IMAGE` name and `allsul/education_auth` image name (also add access to dockerhub via **Advanced**);
* Define pipeline script from SCM
  * Enter GitHub repository url and credentials
  * Enter `${BRANCH}` value to **Branch Specifier** 
  * Enter **Script Path** as `jenkins/deploy_pipeline/Jenkinsfile`
  * Disable **Lightweight checkout**
* Your pipeline is ready to work