package com.synchrony.enums;

public enum AcademicCourse {
    // Arts Courses
    BA("Bachelor of Arts"),
    BA_ENGLISH("B.A. in English Literature"),
    BA_HISTORY("B.A. in History"),
    BA_SOCIOLOGY("B.A. in Sociology"),
    BA_PSYCHOLOGY("B.A. in Psychology"),
    BA_POLITICAL_SCIENCE("B.A. in Political Science"),
    BA_ECONOMICS("B.A. in Economics"),
    BA_PHILOSOPHY("B.A. in Philosophy"),
    BA_FINE_ARTS("B.A. in Fine Arts"),

    // Commerce Courses
    BCOM("Bachelor of Commerce"),
    BCOM_ACCOUNTING("B.Com in Accounting and Finance"),
    BCOM_BANKING("B.Com in Banking and Insurance"),
    BCOM_BUSINESS_ADMIN("B.Com in Business Administration"),
    BCOM_FINANCIAL_MARKETS("B.Com in Financial Markets"),

    // Science Courses
    BSC("Bachelor of Science"),
    BSC_MATHS("B.Sc. in Mathematics"),
    BSC_PHYSICS("B.Sc. in Physics"),
    BSC_CHEMISTRY("B.Sc. in Chemistry"),
    BSC_BIOLOGY("B.Sc. in Biology"),
    BSC_BIOTECH("B.Sc. in Biotechnology"),
    BSC_COMPUTER_SCIENCE("B.Sc. in Computer Science"),
    BSC_IT("B.Sc. in Information Technology"),

    // Technology and Professional Courses
    BCA("Bachelor of Computer Applications"),
    BBA("Bachelor of Business Administration"),
    BTECH("Bachelor of Technology"),
    BSC_NURSING("B.Sc. in Nursing"),
    BPHARM("Bachelor of Pharmacy"),

    // Other Professional Courses
    BED("Bachelor of Education"),
    BFA("Bachelor of Fine Arts"),
    BDES("Bachelor of Design"),
    BARCH("Bachelor of Architecture");

    private final String fullName;

    AcademicCourse(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
