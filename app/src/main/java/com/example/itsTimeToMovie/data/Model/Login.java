package com.example.itsTimeToMovie.data.Model;

import android.content.Context;

import com.example.itsTimeToMovie.data.database.FactoryDataBase;
import com.example.itsTimeToMovie.data.database.dao.UserDAO;
import com.example.itsTimeToMovie.data.database.exception.IncorrectPassword;
import com.example.itsTimeToMovie.data.database.exception.IncorrectUser;

public class Login {
    private static final User USUARIO_NAO_ENCONTRADO = null;

    private UserDAO usuarioDAO;


    public Login(Context context) {
        usuarioDAO = FactoryDataBase.getInstanceAppDataBase(context).getUsuarioDAO();

        User adm = new User("adm", "1234");

        if(usuarioDAO.searchUser("adm") == null) {
            usuarioDAO.save(adm);
        }

    }

    public User entrar(String usuario, String senha) throws IncorrectUser, IncorrectPassword {
        User usuarioBuscado = usuarioDAO.searchUser(usuario);

        if(usuarioBuscado == USUARIO_NAO_ENCONTRADO) throw new IncorrectUser();

        if(!usuarioBuscado.getPassword().equals(senha)) throw new IncorrectPassword();

        return usuarioBuscado;
    }
}
