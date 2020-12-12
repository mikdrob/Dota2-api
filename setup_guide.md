# Setup Guide

Created two virtual machines with **Ubuntu 20.04**
- Prod server: `95.217.129.128`
- Build server: `95.216.214.215`

Attached internal network adapter `192.168.0.0/24`
- Prod server: `192.168.0.2` (**prod.internal**)
- Build server: `192.168.0.3` (**build.internal**)

## Connecting via SSH

To connect to build server:
```
ssh user@95.216.214.215
```

To connect to prod server, we have to jump through build server as SSH is restricted to internal network:
```
ssh -J user@95.216.214.215 user@prod.internal
```

You may use the following SSH config:
```
Host build
  HostName 95.216.214.215
  User joyon
Host prod
  HostName prod.internal
  User joyon
  ProxyCommand C:\Windows\System32\OpenSSH\ssh.exe -Y build -W %h:%p
```

Note that the `ProxyCommand` directive was added due to Windows SSH issues with hopping. You may ignore in Linux.

## SSH Configuration

To secure SSH services on both servers, disable passwordless login and do not permit passwordless login. The following directives must be set:

```
PermitRootLogin no
PasswordAuthentication no
```

Restart SSH:
```
sudo service ssh restart
```

## Iptables

To secure the infrastructure, run the following `iptables` rules:

```
iptables -F # Flush existing rules
iptables -A INPUT -s 192.168.0.0/24 -j ACCEPT
iptables -A INPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT
iptables -A INPUT -i lo -j ACCEPT
iptables -A INPUT -p tcp --dport 80
iptables -A INPUT -p tcp -j DROP
```

These rules translate to:
- **Allow all traffic from internal network 192.168.0.0/24**
- **Allow ESTABLISHED,RELATED connections**
- **Allow incoming 80/tcp from the outside world**
- **Drop everything else**

Install `iptables-persistent` to make iptables rules persistent:
```
sudo apt install iptables-persistent
```

Moreover, nginx is configured to allow incoming requests **ONLY** from cloudflare IP ranges. See [cloudflare setup step](#Cloudflare-Setup) for more info.

## Docker installation


Install `docker` in build server (for gitlab-runner) and prod (for deployment).

```

# Update the apt package index and install packages to allow apt to use a repository over HTTPS:
sudo apt-get update

sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common

# Add Docker’s official GPG key:
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

# Verify the key with the fingerprint 9DC8 5822 9FC7 DD38 854A  E2D8 8D81 803C 0EBF CD88, , by searching for the last 8 characters of the fingerprint.
sudo apt-key fingerprint 0EBFCD88

# set up the stable repository:
sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"

# INSTALL DOCKER ENGINE
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io

# Verify that Docker Engine is installed
sudo docker run hello-world
```

Install `docker-compose` in build server:
```
sudo apt install docker-compose
```
