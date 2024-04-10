FROM nginx:latest

# Copy the build artifacts from the build image to the Nginx image
COPY web/build /usr/share/nginx/html

# Expose port for Nginx
EXPOSE 80

# Command to run the Nginx
CMD ["nginx", "-g", "daemon off;"]
