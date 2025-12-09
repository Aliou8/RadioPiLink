meta-radiopilink
=================

Description
-----------
meta-radiopilink est une couche Yocto/OpenEmbedded fournissant les recettes,
configurations et fichiers nécessaires pour construire une image Linux adaptée
au projet RadioPiLink (daemon, configurations et tools spécifiques).

Dépendances
-----------
- Yocto Project / Poky 
- bitbake
- meta-raspberrypi 
- autres layers requis listés dans conf/layer.conf

Installation rapide
-------------------
1. Placez la couche dans votre répertoire de sources Yocto (ex: poky/sources) :
  ```bash
  git clone <repo-meta-radiopilink> meta-radiopilink
  ```
2. Ajoutez la couche à bblayers dans conf/bblayers.conf :
    meta-radiopilink
    
3. Dans conf/local.conf, définissez la MACHINE appropriée et ajoutez toute
    variable spécifique au projet .
  ```bash
    IMAGE_INSTALL:append = " radiopilink "
    DISTRO = "radiopilinkdistro"
  ```

Construction
-----------
Exemples de commandes depuis l'environnement Yocto (après avoir sourcé oe-init-build-env) :

- Construire une image minimale :
  bitbake core-image-minimal

- Construire l'image RadioPiLink fournie :
  ```bash
    bitbake radiopilink
  ```

Déploiement
----------
Après construction, récupérez l'image générée dans tmp/deploy/images/<MACHINE>.
Pour flasher une carte SD (exemple avec dd) :

  ```bash
  sudo dd if=<image>.sdimg of=/dev/sdX bs=4M conv=fsync
  ```

Remarques de debug
------------------
- Consulter les logs de bitbake (tmp/work/.../temp/log.do_configure, etc.)
- Monter l'image rootfs pour inspecter /etc, /usr/bin et les services
- Activer systemd journal sur la cible pour voir les erreurs du daemon

Structure de la couche
----------------------

Licence
-------
Voir le fichier LICENSE à la racine du dépôt pour les détails de licence.

Contact
-------
Pour questions ou rapports de bugs, ouvrez une issue dans le dépôt GitHub du projet.