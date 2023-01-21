nodeos \
  -e -p eosio \
  --data-dir /home/eosio-node/eosio/data     \
  --config-dir /home/eosio-node/eosio/config \
  --plugin eosio::producer_plugin      \
  --plugin eosio::chain_plugin         \
  --plugin eosio::chain_api_plugin     \
  --plugin eosio::http_plugin          \
  --plugin eosio::state_history_plugin \
  --hard-replay-blockchain             \
  --max-transaction-time=50000         \
  --contracts-console   \
  --disable-replay-opts \
  --access-control-allow-origin='*' \
  --http-validate-host=false        \
  --verbose-http-errors             \
  --state-history-dir /shpdata \
  --trace-history              \
  --chain-state-history        \
  >> nodeos.log 2>&1 &
