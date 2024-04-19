# Check if a new IP address is provided as an argument
if [ -z "$1" ]; then
    echo "Usage: $0 <new_ip_address>"
    exit 1
fi

# New IP address to replace
new_ip="$1"

# List of files to modify
files=("/home/ec2-user/ICDE-JobHive/web/src/constants.ts" "/home/ec2-user/ICDE-JobHive/src/main/java/Helper/Helper.java" "/home/ec2-user/ICDE-JobHive/src/main/java/CORSFilter.java" "/home/ec2-user/ICDE-JobHive/web/config/nginx.conf")

# IP address to replace
old_ip="localhost"

# Loop through each file and replace the IP address
for file in "${files[@]}"; do
    # Check if the file exists
    if [ -f "$file" ]; then
        # Replace the IP address using sed
        sed -i "s/$old_ip/$new_ip/g" "$file"
        echo "Replaced $old_ip with $new_ip in $file"
    else
        echo "File $file does not exist."
    fi
done
