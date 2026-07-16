podTemplate(
    containers: [

        containerTemplate(
            name: 'maven',
            image: 'maven:3.9.9-eclipse-temurin-21',
            command: 'cat',
            ttyEnabled: true
        ),

        containerTemplate(
            name: 'kaniko',
            image: 'gcr.io/kaniko-project/executor:debug',
            command: '/busybox/sh',
            args: '-c cat',
            ttyEnabled: true
        ),

        containerTemplate(
            name: 'kubectl',
            image: 'bitnami/kubectl:latest',
            command: 'cat',
            ttyEnabled: true
        )

    ]
) {

node(POD_LABEL) {

    stage('Checkout') {
        checkout scm
    }

    stage('Test') {
        container('maven') {
            sh 'mvn test'
        }
    }

    stage('Package') {
        container('maven') {
            sh 'mvn clean package'
        }
    }

}
}