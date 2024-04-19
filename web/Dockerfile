FROM nginx:latest

# Copy the build artifacts from the build image to the Nginx image
COPY build /usr/share/nginx/html
COPY /config/nginx.conf /etc/nginx/

# Expose port for Nginx
EXPOSE 80

# Command to run the Nginx
CMD ["nginx", "-g", "daemon off;"]
