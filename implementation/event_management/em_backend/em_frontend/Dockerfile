# Base image
FROM node:14-alpine

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application files to the working directory
COPY . .

# Build the application
RUN npm run build

# Set environment variables, if required
# ENV REACT_APP_API_URL=https://api.example.com

# Expose the desired port (e.g., 3000) for the application
EXPOSE 3000

# Command to start the application
CMD ["npm", "start"]