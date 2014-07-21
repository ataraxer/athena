export LANGUAGE=en_US.UTF-8
export LANG=en_US.UTF-8
export LC_ALL=en_US.UTF-8
locale-gen en_US.UTF-8
dpkg-reconfigure locales

apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10
echo 'deb http://downloads-distro.mongodb.org/repo/ubuntu-upstart dist 10gen' \
  > /etc/apt/sources.list.d/mongodb.list
apt-get update -y > /dev/null
cp /vagrant/mongod.conf /etc/mongod.conf
yes N | apt-get install -y mongodb-org

exit
