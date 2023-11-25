# Use an official Node.js runtime to build web
FROM node:14 AS web_build

# Set the working directory in the container
WORKDIR /usr/src/app1

# Copy package.json and package-lock.json to the working directory
COPY ./web/package*.json ./

# Install dependencies
RUN npm install

# Copy the application code to the container
COPY ./web/ .

# Build the application
RUN npm run build

# Use an official Node.js runtime to build resume
FROM node:14 AS resume_build

# Set the working directory in the container
WORKDIR /usr/src/app2

# Copy package.json and package-lock.json to the working directory
COPY ./resume/package*.json ./

# Install dependencies
RUN npm install

# Copy the application code to the container
COPY ./resume/ .

# Build the application
RUN npm run build

# Use an official Nginx image as a runtime image
FROM nginx:latest

# Copy the build artifacts from the build image to the Nginx image
COPY --from=web_build /usr/src/app1/build /usr/share/nginx/html
COPY --from=resume_build /usr/src/app2/build /usr/share/nginx/html/resume