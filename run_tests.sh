#!/bin/bash

# Master Data Tests - Quick Launcher
# Usage: ./run_tests.sh [python|node]

set -e

echo "=========================================="
echo "Master Data - Products & Services Tests"
echo "=========================================="
echo ""

# Determine which version to run
if [ "$1" == "node" ] || [ "$1" == "js" ]; then
    echo "Running JavaScript version (Playwright)..."
    echo ""

    # Check if node_modules exists
    if [ ! -d "node_modules" ]; then
        echo "Installing dependencies..."
        npm install
        echo ""
    fi

    echo "Starting tests..."
    npm test

elif [ "$1" == "python" ] || [ "$1" == "py" ] || [ -z "$1" ]; then
    echo "Running Python version (Selenium)..."
    echo ""

    # Check Python version
    if ! command -v python3 &> /dev/null; then
        echo "Error: Python 3 is not installed"
        exit 1
    fi

    # Check if virtual environment exists
    if [ ! -d "venv" ]; then
        echo "Creating virtual environment..."
        python3 -m venv venv
        echo "Activating virtual environment..."
        source venv/bin/activate
        echo ""
    else
        echo "Activating virtual environment..."
        source venv/bin/activate
    fi

    echo "Installing dependencies..."
    pip install -q -r requirements.txt
    echo ""

    echo "Starting tests..."
    python test_automation.py

    echo ""
    echo "Deactivating virtual environment..."
    deactivate

else
    echo "Usage: ./run_tests.sh [python|node]"
    echo ""
    echo "Examples:"
    echo "  ./run_tests.sh              # Run Python version (default)"
    echo "  ./run_tests.sh python       # Run Python version"
    echo "  ./run_tests.sh py           # Run Python version"
    echo "  ./run_tests.sh node         # Run JavaScript version"
    echo "  ./run_tests.sh js           # Run JavaScript version"
    exit 1
fi

echo ""
echo "=========================================="
echo "Tests Completed"
echo "=========================================="
echo ""
echo "View results:"
echo "  Report: cat test-report.json"
echo "  Screenshots: ls test-screenshots/"
echo ""
