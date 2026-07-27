podTemplate(
    containers: [

        containerTemplate(
            name: 'maven',
            image: 'maven:3.9.11-eclipse-temurin-21',
            ttyEnabled: true,
            command: 'cat'
        ),

        containerTemplate(
            name: 'kubectl',
            image: 'bitnami/kubectl:latest',
            ttyEnabled: true,
            command: 'cat'
        ),

        containerTemplate(
            name: 'kaniko',
            image: 'gcr.io/kaniko-project/executor:debug',
            ttyEnabled: true,
            command: '/busybox/sh',
            args: '-c cat'
        )

    ],

    volumes: [

        secretVolume(
            secretName: 'dockerhub-secret',
            mountPath: '/kaniko/.docker'
        )

    ]

) {

    node(POD_LABEL) {

        stage('Checkout') {
            checkout scm
        }

        stage('Run Unit Tests') {
            container('maven') {
                sh '''
                    mvn test
                '''
            }
        }

        stage('Package Application') {
            container('maven') {
                sh '''
                    mvn clean package -DskipTests
                '''
            }
        }

        stage('Build & Push Docker Image') {
            container('kaniko') {
                sh '''
                    /kaniko/executor \
                      --context "${WORKSPACE}" \
                      --dockerfile "${WORKSPACE}/Dockerfile" \
                      --destination preetim28/springboot-ci-cd:latest \
                      --destination preetim28/springboot-ci-cd:${BUILD_NUMBER}
                '''
            }
        }

        stage('Verify Image') {
            echo "Docker image successfully pushed to Docker Hub."
            echo "Latest Tag      : latest"
            echo "Build Tag       : ${BUILD_NUMBER}"
        }

    }

}