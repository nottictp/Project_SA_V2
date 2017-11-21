public class SQLiteDatabase {
    public String url = "SeedProduct.db";

    private void prepareConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:" + url;
            //Connection conn = DriverManager.getConnection(dbURL);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
