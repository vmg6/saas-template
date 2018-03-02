#!/bin/bash -e
sudo /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/project-talan/talan-core/latest/install.sh)"
sudo tln install java,docker,docker-compose,maven,nodejs
