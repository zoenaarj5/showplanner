package org.kavus.inyacost.ntt;

public enum LanguageCode {
    DE,EN,ES,FR,NL;

    public static LanguageCode getCurrentLanguageCode() {
        return currentLanguageCode;
    }

    public static void setCurrentLanguageCode(LanguageCode currentLanguageCode) {
        LanguageCode.currentLanguageCode = currentLanguageCode;
    }
    public static final LanguageCode DEFAULT_LANGUAGE_CODE= LanguageCode.EN;
    protected static LanguageCode currentLanguageCode=DEFAULT_LANGUAGE_CODE;
}
