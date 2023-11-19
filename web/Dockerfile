# Use an official Node.js runtime as a parent image
FROM node:14 AS build

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the application code to the container
COPY . .

# Build the application
RUN npm run build

# Use an official Nginx image as a runtime image
FROM nginx:latest

# Copy the build artifacts from the build image to the Nginx image
COPY --from=build /usr/src/app/build /usr/share/nginx/html
