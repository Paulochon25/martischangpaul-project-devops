#!groovy

pipeline {
    agent any
    tools {
        maven 'maven'
    }
    environment {
        PROJET = 'martischangpaul-project-devops'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '100'))
        ansiColor('xterm')
    }
    stages {

        // ----------------------- //
        // On récupère le repo git //
        // ----------------------- //

        stage('git-repo') {
            steps {
                git branch: "${params.BRANCH}",
                       url: 'https://github.com/Ozz007/sb3t.git'
            }
        }

        // ------------------------------- //
        // On crée le Jar à partir du repo //
        // ------------------------------- //

        stage('Maven Compile') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Maven Unit test') {
            when {
                expression { params.SKIP_TESTS == false }
            }
            steps {
                sh 'mvn test'
            }
        }
        // ----------------------- //
        // clean & package project //
        // ----------------------- //
        stage('Maven Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('Rename jar') {
            steps {
                sh "mv sb3t-ws/target/sb3t-ws-1.0-SNAPSHOT.jar SB3T-${params.VERSION}-${params.VERSION_TYPE}.jar"
            }
        }
        // ---------------- //
        // integration test //
        // ---------------- //
        stage('Maven Integ test') {
            when {
                expression { params.SKIP_TESTS == false }
            }

            steps {
                sh 'mvn verify'
            }
        }

    }
}