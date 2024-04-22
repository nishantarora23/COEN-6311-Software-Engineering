pipeline {
    agent {
        node {
            label 'Master'
        }
    }
    environment {
        GRADLE_HOME = '/opt/gradle/gradle-8.7'
        PATH = "$PATH:$GRADLE_HOME/bin"
    }
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout JobHive code from GitHub
                checkout scmGit(branches: [[name: '*/jenkins']], extensions: [cleanBeforeCheckout(deleteUntrackedNestedRepositories: true), pruneStaleBranch(), cloneOption(noTags: false, reference: '', shallow: false, timeout: 3)], userRemoteConfigs: [[url: 'https://github.com/nishantarora23/ICDE-JobHive.git']])
            }
        }
        
        stage('Build Backend') {
            steps {
                sh 'gradle clean build -x test'
            }
        }
        
        stage('Build Frontend') {
            steps {
                dir('/var/lib/jenkins/workspace/JobHive/webapp/web/') {
                    sh '''
                        npx update-browserslist-db@latest
                        npm install && npm run build
                    '''
                }
            }
        }
    }
}