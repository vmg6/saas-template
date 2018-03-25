node {
  //
  def pullRequest = false
  def repo = ''
  def org = ''
  def groupId = ''
  def artifactId = ''
  def version = ''
  //
  echo "SONARQUBE_SERVER: ${SONARQUBE_SERVER}"
  echo "SONARQUBE_SCANNER: ${SONARQUBE_SCANNER}"
  echo "SONARQUBE_ACCESS_TOKEN: ${SONARQUBE_ACCESS_TOKEN}"
  echo "GITHUB_ACCESS_TOKEN: ${GITHUB_ACCESS_TOKEN}"
  echo "NEXUS_HOST: ${NEXUS_HOST}"
  echo "NEXUS_REPO: ${NEXUS_REPO}"
  echo "NEXUS_USER: ${NEXUS_USER}"
  echo "NEXUS_PASS: ${NEXUS_PASS}"
  echo "SERVICES_GJ_PORT: ${SERVICES_GJ_PORT}" 
  
  stage('Clone sources') {
    //
    def scmVars = checkout scm
    if (params.containsKey('sha1')){
      pullRequest = true
      echo "Pull request build sha1: ${sha1}"
      sh "git fetch --tags --progress origin +refs/pull/*:refs/remotes/origin/pr/*"
      sh "git checkout ${ghprbActualCommit}"
    } else {
      echo "Build push branch: ${scmVars.GIT_BRANCH}, sha: ${scmVars.GIT_COMMIT}"
      sh "git checkout ${scmVars.GIT_COMMIT}"
    }
    echo sh(returnStdout: true, script: 'env')
    //
    dir('services/grizzly-jersey'){
      groupId = sh(returnStdout: true, script:'''mvn help:evaluate -Dexpression=project.groupId | grep -e "^[^\\[]"''').trim()
      artifactId = sh(returnStdout: true, script:'''mvn help:evaluate -Dexpression=project.artifactId | grep -e "^[^\\[]"''').trim()
      version = sh(returnStdout: true, script:'''mvn help:evaluate -Dexpression=project.version | grep -e "^[^\\[]"''').trim()
    }
    //
    repo = sh(returnStdout: true, script:'''git config --get remote.origin.url | rev | awk -F'[./:]' '{print $2}' | rev''').trim()
    org = sh(returnStdout: true, script:'''git config --get remote.origin.url | rev | awk -F'[./:]' '{print $3}' | rev''').trim()
    //
    echo "groupId: ${groupId} artifactId: ${artifactId} version: ${version}"
    echo "org: ${org} repo: ${repo}"
    //
    sh "envsubst < .env.template > .env"
    sh "cat ./.env"
    sh "envsubst < settings.xml.template > settings.xml"
    sh "cat ./settings.xml"
  }
  //
  //
  stage('Deploy to QA Stage') {
    if (pullRequest){
    } else {
      sh "./upload.sh ${groupId} ${artifactId} ${version} ./services/grizzly-jersey/target"
    }
    //archiveArtifacts artifacts: 'mobile/platforms/android/build/outputs/apk/*.apk'
  }
  
  stage("Integration testing") {
        git url: 'https://github.com/vmg6/saas-fremework-camp.git'
        sh "mvn clean test -Dservers=env1"
  }

  //
    stage('Request approval for deploy to Stage') {
      script {
          timeout(time:10, unit:'MINUTES') {
              while (true) {
                  userPasswordInput = input(id: 'userPasswordInput',
                      message: 'Please approve deploy to Stage. Enter password to proceed.',
                      parameters: [[$class: 'StringParameterDefinition', defaultValue: '',  name: 'Password']])
                  if (userPasswordInput=='Yes') { break }
              }
          }
      }
    }

    //
    stage('Deploy to Stage') {
        // TBD
      }
}
