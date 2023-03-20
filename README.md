# Projet de Qualité Logiciel
[Website report](https://xerox123dshdhwx.github.io/projetQualite-Logiciel/project-reports.html
## Rapport Coverage & Test 
``mvn install``

## Run test
``mvn test -Dmaven.test.failure.ignore=true``

## Run all rapport on local website
``mvn test surefire-report:report -Dmaven.test.failure.ignore=true``

## Deploy on a GitHub pages all the rapport (test, coverage, javadoc)

``mvn site-deploy``

## Configuration of the settings.xml to run the `site-deploy` command 
Your `settings.xml` should live in your `.m2` folder.

### On Windows `.m2` location:

> c:\Users\myName\.m2

### On Mac `.m2` location:

> /Users/myName/.m2/settings.xml

### If you do not have a settings.xml file, you will have to make one.

Make a file in your `.m2` folder called `settings.xml`
### Write on your `settings.xml` file the OauthToken of you GitHub repository

With that maven will be connected to your GitHub repo to push your file target/site to a page named `gh-pages` 

#### Important : need to give some users permission when you create your token
```
<settings xmlns="http://maven.apache.org/SETTINGS/1.1.0">
    <servers>
        <server>
            <id>github</id>
            <password>Personal access tokens</password>
        </server>
    </servers>
</settings>
```
### Add the GitHub plugin for maven in your xml
[See the exemple on our pom.xml](https://github.com/xerox123dshdhwx/projetQualite-Logiciel/blob/main/pom.xml#L96)

#### Sources 
[Config your .m2 folder](https://help.mulesoft.com/s/article/Where-is-my-settings-xml-file)

[Config your maven GitHub plugin](https://jeanchristophegay.com/posts/publier-son-site-maven-sur-github-pages/)
