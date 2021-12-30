<h1>TP DevOps</h1>


## Consignes du projet

Le but de ce projet est de mettre en pratique l’ensemble des connaissances acquises
pendant les 4 journées de formation, afin de réaliser une infrastructure de staging pour une
application java (fournie).

Le rendu se doit d'être un projet git de type monorepo (contenant le code IaC
terraform, CaC ansible et les pipelines de déploiements jenkins (dans des dossiers différents
pour la clarté)). Ce projet doit tendre à respecter le plus possible le “git flow”.

La propreté des commits et du code (ansible, jenkins et terraform) est primordiale dans la
notation du projet.


## Organisation des fichiers

- Chaque partie du TP aura un répertoire dédié.
- Le README.md et la partie docker (`Dockerfile`, `docker-compose.yml`) sont présents à la racine du projet. Ces derniers fichiers permettront de construire l'infrastructure du projet.


## Mise en place de l'environnement

Mise en place du projet en local pour les utilisateurs

### <u>Prérequis</u>

Vous aurez besoin d'installer [Docker Dekstop](https://docs.docker.com/desktop/) 

### <u>Récupération du projet</u>

<bold>Assurez vous d'avoir Docker de lancé avant de poursuivre la mise en place du projet.</bold>

- Clonez le repository git
   ```sh
   git clone git@github.com:Paulochon25/martischangpaul-project-devops.git
   ```
- Déplacez vous dans le projet
   ```sh
   cd /martischangpaul-project-devops
   ```
- On va build l'image docker à l'aide de cette commande :
   ```sh
   docker-compose up --build
   ```
 - L'environnement est lancé ! Maintenant, dirigez vous sur le port 80 de votre [http://localhost](http://localhost/login?from=%2F)

### Usage

Vous êtes arrivés sur l'interface Jenkins ? Good, maintenant connectez-vous avec ces identifiants :
* Nom d'utilisateur = `admin`
* Mot de passe = `admin`

Une fois connecté vous aurez accès à deux dossiers, CI et CD.
<u>Le répertoire CI (Continuous Integration)</u> contient la Pipeline de build qui permettait la construction du .jar
<u>Quant au répertoire CD (Continuous Deploy) il est divisé en 4 sous-dossiers :</u>
<li><strong>SSH</strong>, pipeline qui permet de générer une clé SSH pour l'instance. Une fois lancée, cette dernière sera disponible dans la console.</li>
<li><strong>Terraform</strong> quant à lui permet de créer l'instance AWS.</li>
<li><strong>Ansible</strong> permet l'exploitation de l'instance AWS.</li>
<li><strong>Terraform-destroy</strong> permet, comme son nom l'indique, de détruire cette instance.</li>

### <u>Problèmes rencontrés</u>

On a voulu utiliser deux playbook ( `monitor-playbook.yml` & `playbook.yml` ) mais nous n'avons pas réussi à faire fonctionner les deux en même temps, Jenkins ne nous permet pas de lancer plusieurs build simultanément.


L'exécution du playbook ansible dans la pipeline nous donne cette erreur alors qu'avec Jenkins on a réussi à se connecter : `java.nio.file.AccessDeniedException: /usr/share/ansible@tmp`.

Alors que cette erreur n'est pas présente sur le cli de docker, les commandes ci-dessous fonctionnent bien dessus quand on accède bien au playbook du dockerFile.

```sh
 cd /usr/share/ansible
 ansible-playbook playbook.yml -i hosts --private-key ~/.ssh/id_rsa
```

## Documentation

* [Ansible](https://docs.ansible.com/)
* [Jenkins](https://www.jenkins.io/)
* [Terraform](https://www.terraform.io/)
* [Docker](https://www.docker.com/)


