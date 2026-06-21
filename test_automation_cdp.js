#!/usr/bin/env node

const { parseArgs, printUsage } = require('./automation/master-data/config');
const { runMasterDataScenario } = require('./automation/master-data/tc-md-ps-001');

async function main() {
  const config = parseArgs(process.argv.slice(2));
  if (config.help) {
    printUsage();
    return 0;
  }
  return runMasterDataScenario(config);
}

main()
  .then((exitCode) => process.exit(exitCode))
  .catch((error) => {
    console.error(`Fatal: ${error.message}`);
    process.exit(1);
  });
