package io.jadiefication.minecraft

enum class Versions(val profile: Profile) {
    `1.8.9`(Profile.FORGE), `1.12.2`(Profile.FORGE),
    `1.16.5`(Profile.FABRIC), `1.17.1`(Profile.FABRIC),
    `1.18.2`(Profile.FABRIC), `1.19.2`(Profile.FABRIC),
    `1.19.3`(Profile.FABRIC), `1.19.4`(Profile.FABRIC),
    `1.20`(Profile.FABRIC), `1.20.1`(Profile.FABRIC),
    `1.20.2`(Profile.FABRIC), `1.20.3`(Profile.FABRIC),
    `1.20.4`(Profile.FABRIC), `1.20.5`(Profile.FABRIC),
    `1.20.6`(Profile.FABRIC), `1.21`(Profile.FABRIC),
    `1.21.1`(Profile.FABRIC), `1.21.3`(Profile.FABRIC),
    `1.21.4`(Profile.FABRIC), `1.21.5`(Profile.FABRIC),
    `1.21.6`(Profile.FABRIC),
}

enum class Profile {
    FORGE, FABRIC
}