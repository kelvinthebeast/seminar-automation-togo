# Set the version of Java and Maven to install
$javaVersion = "17"
$mavenVersion = "3.9.0"

# Define installation paths
$javaHome = "C:\Program Files\Java\jdk-$javaVersion"
$mavenHome = "C:\Program Files\Apache\Maven"

# Function to install Java
function Install-Java {
    Write-Host "Installing Java JDK $javaVersion..."

    # URL to download the JDK 17 installer (check for the latest URL from Oracle)
    $javaInstallerUrl = "https://download.oracle.com/java/17/latest/jdk-17_windows-x64_bin.exe"
    $javaInstallerPath = "$env:TEMP\jdk-$javaVersion-installer.exe"

    # Try to download the Java installer
    try {
        Invoke-WebRequest -Uri $javaInstallerUrl -OutFile $javaInstallerPath -ErrorAction Stop
    }
    catch {
        Write-Host "Failed to download Java JDK. Please verify the URL or your internet connection."
        return
    }

    # Check if the installer file exists before proceeding
    if (-Not (Test-Path $javaInstallerPath)) {
        Write-Host "Installer download failed. The file does not exist."
        return
    }

    # Start the installer
    try {
        Start-Process -FilePath $javaInstallerPath -ArgumentList "/s" -Wait -ErrorAction Stop
    }
    catch {
        Write-Host "Failed to start the Java installer. Please verify the downloaded file."
        return
    }

    # Set JAVA_HOME and update PATH
    [System.Environment]::SetEnvironmentVariable('JAVA_HOME', $javaHome, 'Machine')
    [System.Environment]::SetEnvironmentVariable('Path', $env:Path + ";$javaHome\bin", 'Machine')

    Write-Host "Java $javaVersion installed successfully."
}

# Function to install Maven
function Install-Maven {
    Write-Host "Installing Maven $mavenVersion..."
    $mavenInstallerUrl = "https://archive.apache.org/dist/maven/maven-3/$mavenVersion/binaries/apache-maven-$mavenVersion-bin.zip"
    $mavenInstallerPath = "$env:TEMP\apache-maven-$mavenVersion-bin.zip"
    
    # Try to download Maven installer
    try {
        Invoke-WebRequest -Uri $mavenInstallerUrl -OutFile $mavenInstallerPath -ErrorAction Stop
    }
    catch {
        Write-Host "Failed to download Maven. Please check the URL or your internet connection."
        return
    }

    # Extract Maven if the download was successful
    if (-Not (Test-Path $mavenInstallerPath)) {
        Write-Host "Failed to download Maven. The installer file does not exist."
        return
    }

    # Extract the Maven ZIP archive
    Expand-Archive -Path $mavenInstallerPath -DestinationPath "C:\Program Files\Apache"

    # Set MAVEN_HOME and update PATH
    [System.Environment]::SetEnvironmentVariable('MAVEN_HOME', "$mavenHome\apache-maven-$mavenVersion", 'Machine')
    [System.Environment]::SetEnvironmentVariable('Path', $env:Path + ";$mavenHome\apache-maven-$mavenVersion\bin", 'Machine')
    
    Write-Host "Maven $mavenVersion installed successfully."
}

# Function to install project dependencies using Maven
function Install-MavenDependencies {
    Write-Host "Installing project dependencies with Maven..."
    cd $PSScriptRoot

    # Run Maven to download dependencies (including JUnit, Selenium, etc.)
    mvn clean install

    Write-Host "Dependencies installed successfully."
}

# Main script execution
if (-not (Test-Path "$javaHome\bin\java.exe")) {
    Install-Java
}

if (-not (Test-Path "$mavenHome\apache-maven-$mavenVersion\bin\mvn.exe")) {
    Install-Maven
}

# Install dependencies for your project
Install-MavenDependencies
Write-Host "Setup complete! You can now run your project using Maven."


code .
