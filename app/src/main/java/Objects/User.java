package Objects;

public class User {
    String correo;
    String nick;
    float calificacion_total ; /*Aquí se almacena la puntuación definitiva de cada usuario
     La puntuación total será la suma de las calificaciones asignadas entre el numero de calificadores
    calificacion_total= calificacion_suma/num_calificaciones       */
    int num_calificacion; /*Éste se irá incrementando conforme algun otro usuario califique al actual
    No olvidar asignarle 1 a la base de datos, 1 será su valor por default y de ahí se incrementará */
    float calificacion_suma; /*La función de este atributo será acumular las calificaciones que los usuarios le den
    al usuario actual, NO olvidar asignarle 10 en la base de datos, 10 será su valor por default, todos los
    usuario empezarán con una califiación de 10 por default */
    String uri;
    int estado; /* 1: Estado activo, puede realizar publicaciones y demás
                   0: Se encuentra inactivo debido a mal uso de la aplicación
    Tener en cuenta que su valor por defecto para cada usuario será 1 */

    public User() {
    }

    public User(String correo, String nick, float calificacion_total, int num_calificacion, float calificacion_suma, String uri, int estado) {
        this.correo = correo;
        this.nick = nick;
        this.calificacion_total = calificacion_total;
        this.num_calificacion = num_calificacion;
        this.calificacion_suma = calificacion_suma;
        this.uri = uri;
        this.estado = estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public float getCalificacion_total() {
        return calificacion_total;
    }

    public void setCalificacion_total(float calificacion_total) {
        this.calificacion_total = calificacion_total;
    }

    public int getNum_calificacion() {
        return num_calificacion;
    }

    public void setNum_calificacion(int num_calificacion) {
        this.num_calificacion = num_calificacion;
    }

    public float getCalificacion_suma() {
        return calificacion_suma;
    }

    public void setCalificacion_suma(float calificacion_suma) {
        this.calificacion_suma = calificacion_suma;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
