package org.kavus.inyakost.ntt;

public enum LanguageCode {
    DE,EN,ES,FR,NL;
    protected static LanguageCode currentLanguageCode;

    public static LanguageCode getCurrentLanguageCode() {
        return currentLanguageCode;
    }

    public static void setCurrentLanguageCode(LanguageCode currentLanguageCode) {
        LanguageCode.currentLanguageCode = currentLanguageCode;
    }
    public static final LanguageCode DEFAULT_LANGUAGE_CODE= LanguageCode.EN;
}
