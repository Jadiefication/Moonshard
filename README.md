# Moonshard

**Moonshard** is a terminal-based Kotlin application (TUI) that installs [Modrinth](https://modrinth.com) and [CurseForge](https://www.curseforge.com) mods directly into your [Lunar Client](https://www.lunarclient.com) setup on Linux. It automates downloading and installing compatible mods, offering a fast, streamlined experience for customizing your client.

## ✨ Features

* 📦 Install Minecraft mods from **Modrinth** and **CurseForge**
* 📁 Automatically places mods into the Lunar Client directory
* 🧠 Lightweight and simple TUI interface
* ⚖️ Configurable installation path
* 🐧 Linux support only (for now)

## 📦 Requirements

* [Java 17+](https://openjdk.org/projects/jdk/17/)
* [Maven](https://maven.apache.org/)
* Linux OS (tested on major distros)

## 🚀 Installation

### 1. Clone the repository

```bash
git clone https://github.com/yourname/moonshard.git
cd moonshard
```

### 2. Build with Maven

```bash
mvn clean package
```

The JAR will be located in the `target/` directory.

### 3. Run the application

```bash
java -jar target/moonshard.jar
```

## 💠 Usage

When launched, Moonshard walks you through:

1. Selecting or confirming your Lunar Client directory
2. Searching for mods on Modrinth or CurseForge
3. Installing the selected mods into the proper directory

All mods are placed in:

```
<lunar-client-dir>/profiles/lunar/<main-version>/mods/<full-version-with-modloader>
```

For example:

```
~/.lunarclient/profiles/lunar/1.21/mods/fabric-1.21.5
```

You can change the path via the configuration file located at:

```
~/.moonshard/config.json
```

## 🔌 Mod Sources

* [Modrinth API](https://docs.modrinth.com)
* [CurseForge API](https://docs.curseforge.com)

Authentication is not required. All requests use public endpoints.

## ⚠️ Limitations

* ❌ No Windows/macOS support (yet)
* ❌ No offline mode (requires API access)
* ❌ No auto-detection of mod compatibility (coming soon)

## 🧑‍💻 Built With

* [Kotlin](https://kotlinlang.org)
* [Maven](https://maven.apache.org)
* [Ktor](https://ktor.io) for HTTP requests
* [Clikt](https://ajalt.github.io/clikt/) for CLI handling
* [Kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for config and API data

## 📄 License

Licensed under the MIT License. See [LICENSE](./LICENSE) for details.

---

> **Disclaimer:** Moonshard is not affiliated with Lunar Client, Modrinth, or CurseForge.
