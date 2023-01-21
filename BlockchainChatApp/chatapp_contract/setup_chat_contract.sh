rm -rf chat.wasm
rm -rf chat.abi
eosio-cpp -abigen chat.cpp -o chat.wasm > log.txt
cleos set contract chatapp -c
cleos set contract chatapp ./ chat.wasm chat.abi
