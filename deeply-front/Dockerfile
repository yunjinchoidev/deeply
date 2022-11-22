FROM node:16-alpine3.11
WORKDIR ./
COPY package*.json ./
RUN npm install --force
COPY . .
CMD [ "npm", "start" ]
EXPOSE 3000