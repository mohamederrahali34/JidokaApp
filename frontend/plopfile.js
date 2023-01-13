const configGeneratorComponent = {
  description: "this is next boilerplate component generator",
  prompts: [
    {
      type: "input",
      name: "name",
      message: "component name please",
    },
  ],
  actions: [
    {
      type: "add",
      path: "components/{{camelCase name}}/{{pascalCase name}}.tsx",
      templateFile: "__plop__/ComponentReact.hbs",
    },
    {
      type: "add",
      path: "components/{{camelCase name}}/index.ts",
      templateFile: "__plop__/index.hbs",
    },
    {
      type: "add",
      path: "components/{{camelCase name}}/{{pascalCase name}}.style.ts",
    },
  ],
};

const configGeneratorPage = {
  description: "this is next boilerplate page generator",
  prompts: [
    {
      type: "input",
      name: "name",
      message: "page name please",
    },
  ],
  actions: [
    {
      type: "add",
      path: "pages/{{camelCase name}}.tsx",
      templateFile: "__plop__/Page.hbs",
    },
  ],
};

module.exports = function configure(plop) {
  plop.setGenerator("component", configGeneratorComponent);
  plop.setGenerator("page", configGeneratorPage);
};
