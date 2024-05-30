package com.caisse.exception;

public enum ErrorCodes {

        ARTICLE_NOT_FOUND(1000),
        ARTICLE_NOT_VALID(1001),
        ARTICLE_ALREADY_IN_USE(1002),

        CATEGORY_NOT_FOUND(2000),
        CATEGORY_NOT_VALID(2001),
        CATEGORY_ALREADY_IN_USE(2002),

        CLIENT_NOT_FOUND(3000),
        CLIENT_NOT_VALID(3001),
        CLIENT_ALREADY_IN_USE(3002),


        STOCK_UP_NOT_FOUND(5000),
        STOCK_UP_NOT_VALID(5001),
        STOCK_UP_NON_MODIFIABLE(5002),
        STOCK_UP_ALREADY_IN_USE(5003),

        ENTREPRISE_NOT_FOUND(6000),
        ENTREPRISE_NOT_VALID(6001),



        LIGNE_COMMANDE_STOCK_UP_NOT_FOUND(9000),
        LIGNE_FACTURE_NOT_FOUND(10000),

        MVT_STK_NOT_FOUND(11000),
        MVT_STK_NOT_VALID(11001),


        BAD_CREDENTIALS(12003),

        FACTURE_NOT_FOUND(13000),
        FACTURE_NOT_VALID(13001),
        FACTURE_ALREADY_IN_USE(13002),

        // Liste des exception techniaues
        UPDATE_PHOTO_EXCEPTION(14000),
        UNKNOWN_CONTEXT(14001)
        ;

        private int code;

        ErrorCodes(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }