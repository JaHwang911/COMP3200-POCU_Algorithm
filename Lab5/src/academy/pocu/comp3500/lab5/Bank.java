package academy.pocu.comp3500.lab5;

import javax.crypto.Cipher;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;

public class Bank {
    private final ArrayList<byte[]> publicKeys;
    private final ArrayList<Long> amounts;

    public Bank(byte[][] pubKeys, final long[] amounts) {
        this.publicKeys = new ArrayList<>(pubKeys.length);
        this.amounts = new ArrayList<>(amounts.length);

        for (int i = 0; i < pubKeys.length; ++i) {
            this.publicKeys.add(pubKeys[i]);
        }

        for (int i = 0; i < amounts.length; ++i) {
            this.amounts.add(amounts[i]);
        }
    }

    private int getPubKeyIndex(final byte[] inputPubKey) {
        for (int i = 0; i < this.publicKeys.size(); ++i) {
            boolean bIsSame = true;
            byte[] pubKey = this.publicKeys.get(i);
            
            for (int j = 0; j < pubKey.length; ++j) {
                if (pubKey[j] != inputPubKey[j]) {
                    bIsSame = false;
                    break;
                }
            }

            if (bIsSame) {
                return i;
            }
        }

        return -1;
    }

    public long getBalance(final byte[] pubKey) {
        int index = getPubKeyIndex(pubKey);

        if (index == -1) {
            return 0;
        }

        return this.amounts.get(index);
    }

    public boolean transfer(final byte[] from, byte[] to, final long amount, final byte[] signature) {
        if (amount < 1) {
            return false;
        }

        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(amount);

        byte[] amountBytes = buffer.array();
        byte[] transaction = new byte[from.length + to.length + Long.BYTES];
        int sumLength = 0;

        System.arraycopy(from, 0, transaction, sumLength, from.length);

        sumLength += from.length;
        System.arraycopy(to, 0, transaction, sumLength, to.length);

        sumLength += to.length;
        System.arraycopy(amountBytes, 0, transaction, sumLength, amountBytes.length);

        byte[] transactionHash = getSHA256Hash(transaction);
        byte[] plaintext;

        try {
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(from));

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            plaintext = cipher.doFinal(signature);
        } catch (Exception e) {
            return false;
        }

        for (int i = 0; i < plaintext.length; ++i) {
            if (plaintext[i] != transactionHash[i]) {
                return false;
            }
        }

        assert (transactionHash.length == plaintext.length);

        int fromIndex = getPubKeyIndex(from);
        int toIndex = getPubKeyIndex(to);

        if (fromIndex == -1 && toIndex == -1) {
            return false;
        } else if (toIndex == -1) {
            Long fromAmount = this.amounts.get(fromIndex);

            if (fromAmount < amount) {
                return false;
            }

            this.amounts.set(fromIndex, fromAmount - amount);

            this.publicKeys.add(to);
            this.amounts.add(amount);

            return true;
        } else if (fromIndex == -1) {
            return false;
        }

        Long fromAmount = this.amounts.get(fromIndex);
        Long toAmount = this.amounts.get(toIndex);

        if (fromAmount < amount || Long.MAX_VALUE - toAmount < amount) {
            return false;
        }

        this.amounts.set(fromIndex, fromAmount - amount);
        this.amounts.set(toIndex, toAmount + amount);

        return true;
    }

    private static byte[] getSHA256Hash(byte[] transaction) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(transaction);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}