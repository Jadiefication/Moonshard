package io.jadiefication.minecraft

enum class Versions(val version: String, val profile: Profile) {
    V1_8_9("1.8.9", Profile.FORGE), 
    V1_12_2("1.12.2", Profile.FORGE),
    V1_16_5("1.16.5", Profile.FABRIC), 
    V1_17_1("1.17.1", Profile.FABRIC),
    V1_18_2("1.18.2", Profile.FABRIC), 
    V1_19_2("1.19.2", Profile.FABRIC),
    V1_19_3("1.19.3", Profile.FABRIC), 
    V1_19_4("1.19.4", Profile.FABRIC),
    V1_20("1.20", Profile.FABRIC), 
    V1_20_1("1.20.1", Profile.FABRIC),
    V1_20_2("1.20.2", Profile.FABRIC), 
    V1_20_3("1.20.3", Profile.FABRIC),
    V1_20_4("1.20.4", Profile.FABRIC), 
    V1_20_5("1.20.5", Profile.FABRIC),
    V1_20_6("1.20.6", Profile.FABRIC), 
    V1_21("1.21", Profile.FABRIC),
    V1_21_1("1.21.1", Profile.FABRIC), 
    V1_21_3("1.21.3", Profile.FABRIC),
    V1_21_4("1.21.4", Profile.FABRIC), 
    V1_21_5("1.21.5", Profile.FABRIC),
    V1_21_6("1.21.6", Profile.FABRIC);
}

enum class Profile {
    FORGE, FABRIC
}