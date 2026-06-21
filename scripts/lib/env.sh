#!/usr/bin/env bash

source_env_preserving_caller() {
  local file="$1"
  shift
  [[ -f "$file" ]] || return 0

  local name marker value_name
  for name in "$@"; do
    marker="__caller_has_${name}"
    value_name="__caller_value_${name}"
    if [[ "${!name+x}" == "x" ]]; then
      printf -v "$marker" '%s' "1"
      printf -v "$value_name" '%s' "${!name}"
    else
      printf -v "$marker" '%s' "0"
      printf -v "$value_name" '%s' ""
    fi
  done

  # shellcheck disable=SC1090
  source "$file"

  for name in "$@"; do
    marker="__caller_has_${name}"
    value_name="__caller_value_${name}"
    if [[ "${!marker}" == "1" ]]; then
      printf -v "$name" '%s' "${!value_name}"
    fi
    unset "$marker" "$value_name"
  done
}
