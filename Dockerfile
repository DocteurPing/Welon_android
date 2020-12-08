FROM azabost/android-sdk

WORKDIR /usr/app/src/Mobile

RUN mkdir -p /root/.android && touch /root/.android/repositories.cfg

COPY gradlew build.gradle gradle.properties settings.gradle release-keystore.jks keystore.properties ./

COPY gradle/ ./gradle/

COPY app/build.gradle app/proguard-rules.pro ./app/

COPY app/src/main/AndroidManifest.xml ./app/src/main/AndroidManifest.xml

RUN yes | sdkmanager 'platform-tools' && \
    yes | sdkmanager 'platforms;android-28' && \
    yes | sdkmanager 'build-tools;28.0.3' && \
    yes | sdkmanager 'extras;m2repository;com;android;support;constraint;constraint-layout-solver;1.0.2' && \
    yes | sdkmanager 'extras;m2repository;com;android;support;constraint;constraint-layout;1.0.2' && \
    yes | sdkmanager 'extras;google;m2repository' && \
    yes | sdkmanager 'extras;android;m2repository' && \
    yes | sdkmanager 'extras;google;google_play_services'

RUN ./gradlew :resolveDependencies

COPY app/src ./app/src

RUN rm -rf .gradle/ build/ app/build/ app/release/ && ./gradlew clean && \
    ./gradlew :app:assembleRelease && \
    mkdir -p /android && \
    cp ./app/build/outputs/apk/release/app-release.apk /android/welon-app.apk