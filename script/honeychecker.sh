
#!/bin/bash

set -o nounset
set -o errexit

make_salt () {
    head /dev/urandom | uuencode -m - | sed -n 2p | cut -c1-${1:-8};
}

add_user () {
    local salt=$(make_salt)
    local index=$(( ( RANDOM % 100 )  + 1 ))
    curl -d "id=$salt&index=$index" "http://localhost:3000/create" > /dev/null 2>&1
    echo "User created with id = $salt and index = $index"
}

check_user () {
    curl -d "id=$1&index=$2" "http://localhost:3000/"
}

case "$1" in
    add)
        add_user
        ;;
    check)
        check_user $2 $3
        ;;
    *)
        print_usage
        exit 1
        ;;
esac

exit $?
