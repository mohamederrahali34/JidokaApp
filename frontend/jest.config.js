// @ts-check

/** @type {import('ts-jest/dist/types').InitialOptionsTsJest} */
module.exports = {
  testPathIgnorePatterns: ["<rootDir>/.next/", "<rootDir>/node_modules/"],
  preset: "ts-jest",
  testEnvironment: "jsdom",
  setupFilesAfterEnv: ["<rootDir>/__test__/setup-tests.ts"],
  transform: {
    "^.+\\.(js|jsx|ts|tsx)$": ["babel-jest", { presets: ["next/babel"] }],
  },
  testMatch: ["**/*.spec.{js,jsx,ts,tsx}"],
  moduleNameMapper: {
    "\\.(css|scss|less)$": "<rootDir>/__test__/styleMock.d.ts",
    "^~(.*)$": "<rootDir>/$1",
  },
  collectCoverageFrom: ["*/**/*.{js,jsx,ts,tsx}", "!coverage/**", "!**/node_modules/**", "!**/.next/**"],
  snapshotSerializers: ["@emotion/jest/serializer"],
};
