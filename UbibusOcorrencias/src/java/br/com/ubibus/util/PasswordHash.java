package br.com.ubibus.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
public class PasswordHash {

    static final Logger log = Logger.getLogger(PasswordHash.class.getName());

    /**
     * Criptografa uma string utilizando um hash de 256 bits.
     *
     * @param text - texto a ser criptografado
     * @return uma string com o texto criptografado, essa string contem 64
     * caracteres.
     */
    public String hash256(String text) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            log.severe(e.getMessage());
        }

        BigInteger hash = new BigInteger(1, md.digest(text.getBytes()));
        return hash.toString(16);

    }

    /**
     * Criptografa uma string utilizando um hash de 512 bits.
     *
     * @param text - texto a ser criptografado.
     * @return uma string com o texto criptografado, essa string contem 128
     * caracteres.
     */
    public String hash512(String text) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            log.severe(e.getMessage());
        }

        BigInteger hash = new BigInteger(1, md.digest(text.getBytes()));
        return hash.toString(16);

    }
}
