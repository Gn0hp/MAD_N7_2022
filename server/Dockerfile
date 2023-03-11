# Use an official Node.js runtime as the base image
FROM node:16 as base

# Set the working directory
WORKDIR /home/node/app

# Copy the package.json and package-lock.json files
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the app files
COPY . .

#
RUN cp .env.example .env

# Specify the command to run the app
CMD ["npm", "start"]


FROM base as product
# build