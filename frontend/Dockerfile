FROM node:21-alpine
WORKDIR /app

COPY . .

RUN npm ci
RUN npm run build

ENV NODE_ENV production

CMD ["npx","serve","build"]