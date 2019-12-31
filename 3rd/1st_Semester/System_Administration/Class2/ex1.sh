#!/bin/bash

# Enunciado: Escreva um script que dado o nome de um utilizador no input indique os grupos a que este pertence. Pode apresentar um grupo por linha, separados por vírgulas ou outra formatação.

for user in $( cut -d: -f1 etc/passw ) ; do
    groups=''
    for group in $( grep $user etc/group|cut -d: -f1 ) ; do
        groups=$group,$groups
    done
    echo $user':' $groups
done