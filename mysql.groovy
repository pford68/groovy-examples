import groovy.sql.Sql

@Grab(group='mysql', module='mysql-connector-java', version='5.1.18')
@GrabConfig(systemClassLoader = true)
def sql = Sql.newInstance("jdbc:mysql://dv-gem1-db-1.ipa.explorys.net:3306","emra","sFVrbv1olzJiDJSm","com.mysql.jdbc.Driver")
sql.executeUpdate("create database r1_27_1")