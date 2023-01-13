FROM node:lts as dependencies
WORKDIR /nimblenext
COPY package.json yarn.lock ./
RUN yarn install --frozen-lockfile

FROM node:lts as composer
WORKDIR /nimblenext
COPY . .
COPY --from=dependencies /nimblenext/node_modules ./node_modules
RUN yarn build

FROM node:lts as runner
WORKDIR /nimblenext
ENV NODE_ENV production

COPY --from=composer /nimblenext/next.config.js ./
COPY --from=composer /nimblenext/public ./public
COPY --from=composer /nimblenext/.next ./.next
COPY --from=composer /nimblenext/node_modules ./node_modules
COPY --from=composer /nimblenext/package.json ./package.json

EXPOSE 3000
CMD ["yarn", "start"]