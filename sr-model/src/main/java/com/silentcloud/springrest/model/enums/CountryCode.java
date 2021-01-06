/*
 * Copyright (C) 2012 Neo Visionaries Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.silentcloud.springrest.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.silentcloud.springrest.model.enums.base.EnumConst;
import com.silentcloud.springrest.model.enums.base.converter.BaseEnumConstAttributeConverter;
import com.silentcloud.springrest.model.enums.base.helper.EnumConstHelper;

import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;


/**
 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1">ISO 3166-1</a>
 * country code.
 *
 * <p>
 * Enum names of this enum themselves are represented by
 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a>
 * codes. There are instance methods to get the country name ({@link #getName()}), the
 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3" >ISO 3166-1 alpha-3</a>
 * code ({@link #getAlpha3()}) and the
 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric">ISO 3166-1 numeric</a>
 * code ({@link #getId()}).
 * In addition, there are static methods to get a CountryCode instance that
 * corresponds to a given alpha-2/alpha-3/numeric code ({@link #getByCode(String)},
 * {@link #getByCode(int)}).
 * </p>
 *
 * <pre style="background-color: #EEEEEE; margin-left: 2em; margin-right: 2em; border: 1px solid black;">
 * <span style="color: darkgreen;">// EXAMPLE</span>
 *
 * CountryCode cc = CountryCode.{@link #getByCode(String) getByCode}("JP");
 *
 * <span style="color: darkgreen;">// Country name</span>
 * System.out.println("Country name = " + cc.{@link #getName()});                  <span style="color: darkgreen;">// "Japan"</span>
 *
 * <span style="color: darkgreen;">// ISO 3166-1 alpha-2 code</span>
 * System.out.println("ISO 3166-1 alpha-2 code = " + cc.{@link #getAlpha2()});     <span style="color: darkgreen;">// "JP"</span>
 *
 * <span style="color: darkgreen;">// ISO 3166-1 alpha-3 code</span>
 * System.out.println("ISO 3166-1 alpha-3 code = " + cc.{@link #getAlpha3()});     <span style="color: darkgreen;">// "JPN"</span>
 *
 * <span style="color: darkgreen;">// ISO 3166-1 numeric code</span>
 * System.out.println("ISO 3166-1 numeric code = " + cc.{@link #getId()});    <span style="color: darkgreen;">// 392</span>
 * </pre>
 *
 * @author Takahiko Kawasaki
 */
public enum CountryCode implements EnumConst<CountryCode, Integer> {
    // @formatter:off
    /**
     * <a href="http://en.wikipedia.org/wiki/Andorra">Andorra</a>
     */
    AD(16, "Andorra", "AND"),

    /**
     * <a href="http://en.wikipedia.org/wiki/United_Arab_Emirates">United Arab Emirates</a>
     */
    AE(784, "United Arab Emirates", "ARE"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Afghanistan">Afghanistan</a>
     */
    AF(4, "Afghanistan", "AFG"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Antigua_and_Barbuda">Antigua and Barbuda</a>
     */
    AG(28, "Antigua and Barbuda", "ATG"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Anguilla">Anguilla</a>
     */
    AI(660, "Anguilla", "AIA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Albania">Albania</a>
     */
    AL(8, "Albania", "ALB"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Armenia">Armenia</a>
     */
    AM(51, "Armenia", "ARM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Netherlands_Antilles">Netherlands Antilles</a>
     */
    AN(530, "Netherlands Antilles", "ANT"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Angola">Angola</a>
     */
    AO(24, "Angola", "AGO"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Antarctica">Antarctica</a>
     */
    AQ(10, "Antarctica", "ATA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Argentina">Argentina</a>
     */
    AR(32, "Argentina", "ARG"),

    /**
     * <a href="http://en.wikipedia.org/wiki/American_Samoa">American Samoa</a>
     */
    AS(16, "American Samoa", "ASM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Austria">Austria</a>
     */
    AT(40, "Austria", "AUT"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Australia">Australia</a>
     */
    AU(36, "Australia", "AUS"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Aruba">Aruba</a>
     */
    AW(533, "Aruba", "ABW"),

    /**
     * <a href="http://en.wikipedia.org/wiki/%C3%85land_Islands">&Aring;land Islands</a>
     */
    AX(248, "\u212Bland Islands", "ALA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Azerbaijan">Azerbaijan</a>
     */
    AZ(31, "Azerbaijan", "AZE"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Bosnia_and_Herzegovina">Bosnia and Herzegovina</a>
     */
    BA(70, "Bosnia and Herzegovina", "BIH"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Barbados">Barbados</a>
     */
    BB(52, "Barbados", "BRB"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Bangladesh">Bangladesh</a>
     */
    BD(50, "Bangladesh", "BGD"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Belgium">Belgium</a>
     */
    BE(56, "Belgium", "BEL"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Burkina_Faso">Burkina Faso</a>
     */
    BF(854, "Burkina Faso", "BFA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Bulgaria">Bulgaria</a>
     */
    BG(100, "Bulgaria", "BGR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Bahrain">Bahrain</a>
     */
    BH(48, "Bahrain", "BHR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Burundi">Burundi</a>
     */
    BI(108, "Burundi", "BDI"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Benin">Benin</a>
     */
    BJ(204, "Benin", "BEN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Saint_Barth%C3%A9lemy">Saint Barth&eacute;lemy</a>
     */
    BL(652, "Saint Barth\u00E9lemy", "BLM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Bermuda">Bermuda</a>
     */
    BM(60, "Bermuda", "BMU"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Brunei">Brunei Darussalam</a>
     */
    BN(96, "Brunei Darussalam", "BRN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Bolivia">Plurinational State of Bolivia</a>
     */
    BO(68, "Plurinational State of Bolivia", "BOL"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Caribbean_Netherlands">Bonaire, Sint Eustatius and Saba</a>
     */
    BQ(535, "Bonaire, Sint Eustatius and Saba", "BES"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Brazil">Brazil</a>
     */
    BR(76, "Brazil", "BRA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/The_Bahamas">Bahamas</a>
     */
    BS(44, "Bahamas", "BHS"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Bhutan">Bhutan</a>
     */
    BT(64, "Bhutan", "BTN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Bouvet_Island">Bouvet Island</a>
     */
    BV(74, "Bouvet Island", "BVT"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Botswana">Botswana</a>
     */
    BW(72, "Botswana", "BWA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Belarus">Belarus</a>
     */
    BY(112, "Belarus", "BLR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Belize">Belize</a>
     */
    BZ(84, "Belize", "BLZ"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Canada">Canada</a>
     */
    CA(124, "Canada", "CAN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Cocos_(Keeling)_Islands">Cocos (Keeling) Islands</a>
     */
    CC(166, "Cocos Islands", "CCK"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Democratic_Republic_of_the_Congo">The Democratic Republic of the Congo</a>
     */
    CD(180, "The Democratic Republic of the Congo", "COD"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Central_African_Republic">Central African Republic</a>
     */
    CF(140, "Central African Republic", "CAF"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Republic_of_the_Congo">Congo</a>
     */
    CG(178, "Congo", "COG"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Switzerland">Switzerland</a>
     */
    CH(756, "Switzerland", "CHE"),

    /**
     * <a href="http://en.wikipedia.org/wiki/C%C3%B4te_d%27Ivoire">C&ocirc;te d'Ivoire</a>
     */
    CI(384, "C\u00F4te d'Ivoire", "CIV"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Cook_Islands">Cook Islands</a>
     */
    CK(184, "Cook Islands", "COK"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Chile">Chile</a>
     */
    CL(152, "Chile", "CHL"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Cameroon">Cameroon</a>
     */
    CM(120, "Cameroon", "CMR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/China">China</a>
     */
    @JsonEnumDefaultValue
    CN(156, "China", "CHN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Colombia">Colombia</a>
     */
    CO(170, "Colombia", "COL"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Costa_Rica">Costa Rica</a>
     */
    CR(188, "Costa Rica", "CRI"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Cuba">Cuba</a>
     */
    CU(192, "Cuba", "CUB"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Cape_Verde">Cape Verde</a>
     */
    CV(132, "Cape Verde", "CPV"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Cura%C3%A7ao">Cura&ccedil;ao</a>
     */
    CW(531, "Cura/u00E7ao", "CUW"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Christmas_Island">Christmas Island</a>
     */
    CX(162, "Christmas Island", "CXR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Cyprus">Cyprus</a>
     */
    CY(196, "Cyprus", "CYP"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Czech_Republic">Czech Republic</a>
     */
    CZ(203, "Czech Republic", "CZE"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Germany">Germany</a>
     */
    DE(276, "Germany", "DEU"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Djibouti">Djibouti </a>
     */
    DJ(262, "Djibouti", "DJI"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Denmark">Denmark</a>
     */
    DK(208, "Denmark", "DNK"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Dominica">Dominica</a>
     */
    DM(212, "Dominica", "DMA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Dominican_Republic">Dominican Republic</a>
     */
    DO(214, "Dominican Republic", "DOM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Algeria">Algeria</a>
     */
    DZ(12, "Algeria", "DZA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Ecuador">Ecuador</a>
     */
    EC(218, "Ecuador", "ECU"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Estonia">Estonia</a>
     */
    EE(233, "Estonia", "EST"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Egypt">Egypt</a>
     */
    EG(818, "Egypt", "EGY"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Western_Sahara">Western Sahara</a>
     */
    EH(732, "Western Sahara", "ESH"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Eritrea">Eritrea</a>
     */
    ER(232, "Eritrea", "ERI"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Spain">Spain</a>
     */
    ES(724, "Spain", "ESP"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Ethiopia">Ethiopia</a>
     */
    ET(231, "Ethiopia", "ETH"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Finland">Finland</a>
     */
    FI(246, "Finland", "FIN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Fiji">Fiji</a>
     */
    FJ(242, "Fiji", "FJI"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Falkland_Islands">Falkland Islands (Malvinas)</a>
     */
    FK(238, "Falkland Islands", "FLK"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Federated_States_of_Micronesia">Federated States of Micronesia</a>
     */
    FM(583, "Federated States of Micronesia", "FSM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Faroe_Islands">Faroe Islands</a>
     */
    FO(234, "Faroe Islands", "FRO"),

    /**
     * <a href="http://en.wikipedia.org/wiki/France">France</a>
     */
    FR(250, "France", "FRA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Gabon">Gabon </a>
     */
    GA(266, "Gabon", "GAB"),

    /**
     * <a href="http://en.wikipedia.org/wiki/United_Kingdom">United Kingdom</a>
     */
    GB(826, "United Kingdom", "GBR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Grenada">Grenada</a>
     */
    GD(308, "Grenada", "GRD"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Georgia_(country)">Georgia</a>
     */
    GE(268, "Georgia", "GEO"),

    /**
     * <a href="http://en.wikipedia.org/wiki/French_Guiana">French Guiana</a>
     */
    GF(254, "French Guiana", "GUF"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Guernsey">Guemsey</a>
     */
    GG(831, "Guemsey", "GGY"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Ghana">Ghana</a>
     */
    GH(288, "Ghana", "GHA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Gibraltar">Gibraltar</a>
     */
    GI(292, "Gibraltar", "GIB"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Greenland">Greenland</a>
     */
    GL(304, "Greenland", "GRL"),

    /**
     * <a href="http://en.wikipedia.org/wiki/The_Gambia">Gambia</a>
     */
    GM(270, "Gambia", "GMB"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Guinea">Guinea</a>
     */
    GN(324, "Guinea", "GIN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Guadeloupe">Guadeloupe</a>
     */
    GP(312, "Guadeloupe", "GLP"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Equatorial_Guinea">Equatorial Guinea</a>
     */
    GQ(226, "Equatorial Guinea", "GNQ"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Greece">Greece</a>
     */
    GR(300, "Greece", "GRC"),

    /**
     * <a href="http://en.wikipedia.org/wiki/South_Georgia_and_the_South_Sandwich_Islands">South Georgia and the South Sandwich Islands</a>
     */
    GS(239, "South Georgia and the South Sandwich Islands", "SGS"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Guatemala">Guatemala</a>
     */
    GT(320, "Guatemala", "GTM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Guam">Guam</a>
     */
    GU(316, "Guam", "GUM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Guinea-Bissau">Guinea-Bissau</a>
     */
    GW(624, "Guinea-Bissau", "GNB"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Guyana">Guyana</a>
     */
    GY(328, "Guyana", "GUY"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Hong_Kong">Hong Kong</a>
     */
    HK(344, "Hong Kong", "HKG"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Heard_Island_and_McDonald_Islands">Heard Island and McDonald Islands</a>
     */
    HM(334, "Heard Island and McDonald Islands", "HMD"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Honduras">Honduras</a>
     */
    HN(340, "Honduras", "HND"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Croatia">Croatia</a>
     */
    HR(191, "Croatia", "HRV"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Haiti">Haiti</a>
     */
    HT(332, "Haiti", "HTI"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Hungary">Hungary</a>
     */
    HU(348, "Hungary", "HUN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Indonesia">Indonesia</a>
     */
    ID(360, "Indonesia", "IDN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Republic_of_Ireland">Ireland</a>
     */
    IE(372, "Ireland", "IRL"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Israel">Israel</a>
     */
    IL(376, "Israel", "ISR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Isle_of_Man">Isle of Man</a>
     */
    IM(833, "Isle of Man", "IMN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/India">India</a>
     */
    IN(356, "India", "IND"),

    /**
     * <a href="http://en.wikipedia.org/wiki/British_Indian_Ocean_Territory">British Indian Ocean Territory</a>
     */
    IO(86, "British Indian Ocean Territory", "IOT"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Iraq">Iraq</a>
     */
    IQ(368, "Iraq", "IRQ"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Iran">Islamic Republic of Iran</a>
     */
    IR(364, "Islamic Republic of Iran", "IRN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Iceland">Iceland</a>
     */
    IS(352, "Iceland", "ISL"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Italy">Italy</a>
     */
    IT(380, "Italy", "ITA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Jersey">Jersey</a>
     */
    JE(832, "Jersey", "JEY"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Jamaica">Jamaica</a>
     */
    JM(388, "Jamaica", "JAM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Jordan">Jordan</a>
     */
    JO(400, "Jordan", "JOR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Japan">Japan</a>
     */
    JP(392, "Japan", "JPN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Kenya">Kenya</a>
     */
    KE(404, "Kenya", "KEN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Kyrgyzstan">Kyrgyzstan</a>
     */
    KG(417, "Kyrgyzstan", "KGZ"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Cambodia">Cambodia</a>
     */
    KH(116, "Cambodia", "KHM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Kiribati">Kiribati</a>
     */
    KI(296, "Kiribati", "KIR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Comoros">Comoros</a>
     */
    KM(174, "Comoros", "COM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Saint_Kitts_and_Nevis">Saint Kitts and Nevis</a>
     */
    KN(659, "Saint Kitts and Nevis", "KNA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/North_Korea">Democratic People's Republic of Korea</a>
     */
    KP(408, "Democratic People's Republic of Korea", "PRK"),

    /**
     * <a href="http://en.wikipedia.org/wiki/South_Korea">Republic of Korea</a>
     */
    KR(410, "Republic of Korea", "KOR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Kuwait">Kuwait</a>
     */
    KW(414, "Kuwait", "KWT"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Cayman_Islands">Cayman Islands</a>
     */
    KY(136, "Cayman Islands", "CYM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Kazakhstan">Kazakhstan</a>
     */
    KZ(398, "Kazakhstan", "KAZ"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Laos">Lao People's Democratic Republic</a>
     */
    LA(418, "Lao People's Democratic Republic", "LAO"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Lebanon">Lebanon</a>
     */
    LB(422, "Lebanon", "LBN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Saint_Lucia">Saint Lucia</a>
     */
    LC(662, "Saint Lucia", "LCA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Liechtenstein">Liechtenstein</a>
     */
    LI(438, "Liechtenstein", "LIE"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Sri_Lanka">Sri Lanka</a>
     */
    LK(144, "Sri Lanka", "LKA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Liberia">Liberia</a>
     */
    LR(430, "Liberia", "LBR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Lesotho">Lesotho</a>
     */
    LS(426, "Lesotho", "LSO"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Lithuania">Lithuania</a>
     */
    LT(440, "Lithuania", "LTU"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Luxembourg">Luxembourg</a>
     */
    LU(442, "Luxembourg", "LUX"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Latvia">Latvia</a>
     */
    LV(428, "Latvia", "LVA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Libya">Libya</a>
     */
    LY(434, "Libya", "LBY"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Morocco">Morocco</a>
     */
    MA(504, "Morocco", "MAR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Monaco">Monaco</a>
     */
    MC(492, "Monaco", "MCO"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Moldova">Republic of Moldova</a>
     */
    MD(498, "Republic of Moldova", "MDA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Montenegro">Montenegro</a>
     */
    ME(499, "Montenegro", "MNE"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Collectivity_of_Saint_Martin">Saint Martin (French part)</a>
     */
    MF(663, "Saint Martin", "MAF"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Madagascar">Madagascar</a>
     */
    MG(450, "Madagascar", "MDG"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Marshall_Islands">Marshall Islands</a>
     */
    MH(584, "Marshall Islands", "MHL"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Republic_of_Macedonia">The former Yugoslav Republic of Macedonia</a>
     */
    MK(807, "The former Yugoslav Republic of Macedonia", "MKD"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Mali">Mali</a>
     */
    ML(466, "Mali", "MLI"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Myanmar">Myanmar</a>
     */
    MM(104, "Myanmar", "MMR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Mongolia">Mongolia</a>
     */
    MN(496, "Mongolia", "MNG"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Macau">Macao</a>
     */
    MO(446, "Macao", "MAC"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Northern_Mariana_Islands">Northern Mariana Islands</a>
     */
    MP(580, "Northern Mariana Islands", "MNP"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Martinique">Martinique</a>
     */
    MQ(474, "Martinique", "MTQ"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Mauritania">Mauritania</a>
     */
    MR(478, "Mauritania", "MRT"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Montserrat">Montserrat</a>
     */
    MS(500, "Montserrat", "MSR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Malta">Malta</a>
     */
    MT(470, "Malta", "MLT"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Mauritius">Mauritius</a>
     */
    MU(480, "Mauritius", "MUS"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Maldives">Maldives</a>
     */
    MV(462, "Maldives", "MDV"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Malawi">Malawi</a>
     */
    MW(454, "Malawi", "MWI"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Mexico">Mexico</a>
     */
    MX(484, "Mexico", "MEX"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Malaysia">Malaysia</a>
     */
    MY(458, "Malaysia", "MYS"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Mozambique">Mozambique</a>
     */
    MZ(508, "Mozambique", "MOZ"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Namibia">Namibia</a>
     */
    NA(516, "Namibia", "NAM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/New_Caledonia">New Caledonia</a>
     */
    NC(540, "New Caledonia", "NCL"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Niger">Niger</a>
     */
    NE(562, "Niger", "NER"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Norfolk_Island">Norfolk Island</a>
     */
    NF(574, "Norfolk Island", "NFK"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Nigeria">Nigeria</a>
     */
    NG(566, "Nigeria", "NGA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Nicaragua">Nicaragua</a>
     */
    NI(558, "Nicaragua", "NIC"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Netherlands">Netherlands</a>
     */
    NL(528, "Netherlands", "NLD"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Norway">Norway</a>
     */
    NO(578, "Norway", "NOR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Nepal">Nepal</a>
     */
    NP(524, "Nepal", "NPL"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Nauru">Nauru</a>
     */
    NR(520, "Nauru", "NRU"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Niue">Niue</a>
     */
    NU(570, "Niue", "NIU"),

    /**
     * <a href="http://en.wikipedia.org/wiki/New_Zealand">New Zealand</a>
     */
    NZ(554, "New Zealand", "NZL"),

    /**
     * <a href=http://en.wikipedia.org/wiki/Oman"">Oman</a>
     */
    OM(512, "Oman", "OMN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Panama">Panama</a>
     */
    PA(591, "Panama", "PAN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Peru">Peru</a>
     */
    PE(604, "Peru", "PER"),

    /**
     * <a href="http://en.wikipedia.org/wiki/French_Polynesia">French Polynesia</a>
     */
    PF(258, "French Polynesia", "PYF"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Papua_New_Guinea">Papua New Guinea</a>
     */
    PG(598, "Papua New Guinea", "PNG"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Philippines">Philippines</a>
     */
    PH(608, "Philippines", "PHL"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Pakistan">Pakistan</a>
     */
    PK(586, "Pakistan", "PAK"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Poland">Poland</a>
     */
    PL(616, "Poland", "POL"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Saint_Pierre_and_Miquelon">Saint Pierre and Miquelon</a>
     */
    PM(666, "Saint Pierre and Miquelon", "SPM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Pitcairn_Islands">Pitcairn</a>
     */
    PN(612, "Pitcairn", "PCN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Puerto_Rico">Puerto Rico</a>
     */
    PR(630, "Puerto Rico", "PRI"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Palestinian_territories">Occupied Palestinian Territory</a>
     */
    PS(275, "Occupied Palestinian Territory", "PSE"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Portugal">Portugal</a>
     */
    PT(620, "Portugal", "PRT"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Palau">Palau</a>
     */
    PW(585, "Palau", "PLW"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Paraguay">Paraguay</a>
     */
    PY(600, "Paraguay", "PRY"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Qatar">Qatar</a>
     */
    QA(634, "Qatar", "QAT"),

    /**
     * <a href="http://en.wikipedia.org/wiki/R%C3%A9union">R&eacute;union</a>
     */
    RE(638, "R\u00E9union", "REU"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Romania">Romania</a>
     */
    RO(642, "Romania", "ROU"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Serbia">Serbia</a>
     */
    RS(688, "Serbia", "SRB"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Russia">Russian Federation</a>
     */
    RU(643, "Russian Federation", "RUS"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Rwanda">Rwanda</a>
     */
    RW(646, "Rwanda", "RWA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Saudi_Arabia">Saudi Arabia</a>
     */
    SA(682, "Saudi Arabia", "SAU"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Solomon_Islands">Solomon Islands</a>
     */
    SB(90, "Solomon Islands", "SLB"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Seychelles">Seychelles</a>
     */
    SC(690, "Seychelles", "SYC"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Sudan">Sudan</a>
     */
    SD(729, "Sudan", "SDN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Sweden">Sweden</a>
     */
    SE(752, "Sweden", "SWE"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Singapore">Singapore</a>
     */
    SG(702, "Singapore", "SGP"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Saint_Helena,_Ascension_and_Tristan_da_Cunha">Saint Helena, Ascension and Tristan da Cunha</a>
     */
    SH(654, "Saint Helena, Ascension and Tristan da Cunha", "SHN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Slovenia">Slovenia</a>
     */
    SI(705, "Slovenia", "SVN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Svalbard_and_Jan_Mayen">Svalbard and Jan Mayen</a>
     */
    SJ(744, "Svalbard and Jan Mayen", "SJM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Slovakia">Slovakia</a>
     */
    SK(703, "Slovakia", "SVK"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Sierra_Leone">Sierra Leone</a>
     */
    SL(694, "Sierra Leone", "SLE"),

    /**
     * <a href="http://en.wikipedia.org/wiki/San_Marino">San Marino</a>
     */
    SM(674, "San Marino", "SMR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Senegal">Senegal</a>
     */
    SN(686, "Senegal", "SEN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Somalia">Somalia</a>
     */
    SO(706, "Somalia", "SOM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Suriname">Suriname</a>
     */
    SR(740, "Suriname", "SUR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/South_Sudan">South Sudan</a>
     */
    SS(728, "South Sudan", "SSD"),

    /**
     * <a href="http://en.wikipedia.org/wiki/S%C3%A3o_Tom%C3%A9_and_Pr%C3%ADncipe">Sao Tome and Principe</a>
     */
    ST(678, "Sao Tome and Principe", "STP"),

    /**
     * <a href="http://en.wikipedia.org/wiki/El_Salvador">El Salvador</a>
     */
    SV(222, "El Salvador", "SLV"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Sint_Maarten">Sint Maarten (Dutch part)</a>
     */
    SX(534, "Sint Maarten", "SXM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Syria">Syrian Arab Republic</a>
     */
    SY(760, "Syrian Arab Republic", "SYR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Swaziland">Swaziland</a>
     */
    SZ(748, "Swaziland", "SWZ"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Turks_and_Caicos_Islands">Turks and Caicos Islands</a>
     */
    TC(796, "Turks and Caicos Islands", "TCA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Chad">Chad</a>
     */
    TD(148, "Chad", "TCD"),

    /**
     * <a href="http://en.wikipedia.org/wiki/French_Southern_and_Antarctic_Lands">French Southern Territories</a>
     */
    TF(260, "French Southern Territories", "ATF"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Togo">Togo</a>
     */
    TG(768, "Togo", "TGO"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Thailand">Thailand</a>
     */
    TH(764, "Thailand", "THA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Tajikistan">Tajikistan</a>
     */
    TJ(762, "Tajikistan", "TJK"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Tokelau">Tokelau</a>
     */
    TK(772, "Tokelau", "TKL"),

    /**
     * <a href="http://en.wikipedia.org/wiki/East_Timor">Timor-Leste</a>
     */
    TL(626, "Timor-Leste", "TLS"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Turkmenistan">Turkmenistan</a>
     */
    TM(795, "Turkmenistan", "TKM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Tunisia">Tunisia</a>
     */
    TN(788, "Tunisia", "TUN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Tonga">Tonga</a>
     */
    TO(776, "Tonga", "TON"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Turkey">Turkey</a>
     */
    TR(792, "Turkey", "TUR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Trinidad_and_Tobago">Trinidad and Tobago</a>
     */
    TT(780, "Trinidad and Tobago", "TTO"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Tuvalu">Tuvalu</a>
     */
    TV(798, "Tuvalu", "TUV"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Taiwan">Taiwan, Province of China</a>
     */
    TW(158, "Taiwan, Province of China", "TWN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Tanzania">United Republic of Tanzania</a>
     */
    TZ(834, "United Republic of Tanzania", "TZA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Ukraine">Ukraine</a>
     */
    UA(804, "Ukraine", "UKR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Uganda">Uganda</a>
     */
    UG(800, "Uganda", "UGA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/United_States_Minor_Outlying_Islands">United States Minor Outlying Islands</a>
     */
    UM(581, "United States Minor Outlying Islands", "UMI"),

    /**
     * <a href="http://en.wikipedia.org/wiki/United_States">United States</a>
     */
    US(840, "United States", "USA"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Uruguay">Uruguay</a>
     */
    UY(858, "Uruguay", "URY"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Uzbekistan">Uzbekistan</a>
     */
    UZ(860, "Uzbekistan", "UZB"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Vatican_City">Holy See (Vatican City State)</a>
     */
    VA(336, "Holy See", "VAT"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Saint_Vincent_and_the_Grenadines">Saint Vincent and the Grenadines</a>
     */
    VC(670, "Saint Vincent and the Grenadines", "VCT"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Venezuela">Bolivarian Republic of Venezuela</a>
     */
    VE(862, "Bolivarian Republic of Venezuela", "VEN"),

    /**
     * <a href="http://en.wikipedia.org/wiki/British_Virgin_Islands">British Virgin Islands</a>
     */
    VG(92, "British Virgin Islands", "VGB"),

    /**
     * <a href="http://en.wikipedia.org/wiki/United_States_Virgin_Islands">Virgin Islands, U.S.</a>
     */
    VI(850, "Virgin Islands, U.S.", "VIR"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Vietnam">Viet Nam</a>
     */
    VN(704, "Viet Nam", "VNM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Vanuatu">Vanuatu</a>
     */
    VU(548, "Vanuatu", "VUT"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Wallis_and_Futuna">Wallis and Futuna</a>
     */
    WF(876, "Wallis and Futuna", "WLF"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Samoa">Samoa</a>
     */
    WS(882, "Samoa", "WSM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Yemen">Yemen</a>
     */
    YE(887, "Yemen", "YEM"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Mayotte">Mayotte</a>
     */
    YT(175, "Mayotte", "MYT"),

    /**
     * <a href="http://en.wikipedia.org/wiki/South_Africa">South Africa</a>
     */
    ZA(710, "South Africa", "ZAF"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Zambia">Zambia</a>
     */
    ZM(894, "Zambia", "ZMB"),

    /**
     * <a href="http://en.wikipedia.org/wiki/Zimbabwe">Zimbabwe</a>
     */
    ZW(716, "Zimbabwe", "ZWE"),
    ;
    // @formatter:on


    public static final EnumConstHelper<CountryCode, Integer> HELPER = EnumConstHelper.of(CountryCode.class);
    private static final Map<String, CountryCode> alpha3Map = new HashMap<>();
    private static final Map<Integer, CountryCode> numericMap = new HashMap<>();

    static {
        for (CountryCode cc : values()) {
            alpha3Map.put(cc.getAlpha3(), cc);
            numericMap.put(cc.getId(), cc);
        }
    }

    private final Integer id;
    private final String name;
    private final String alpha3;

    CountryCode(Integer id, String name, String alpha3) {
        this.id = id;
        this.name = name;
        this.alpha3 = alpha3;
    }


    /**
     * Get the country name.
     *
     * @return The country name.
     */
    public String getName() {
        return name;
    }


    /**
     * Get the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2"
     * >ISO 3166-1 alpha-2</a> code.
     *
     * @return The <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2"
     * >ISO 3166-1 alpha-2</a> code.
     */
    public String getAlpha2() {
        return name();
    }


    /**
     * Get the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3"
     * >ISO 3166-1 alpha-3</a> code.
     *
     * @return The <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3"
     * >ISO 3166-1 alpha-3</a> code.
     */
    public String getAlpha3() {
        return alpha3;
    }


    /**
     * Get the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric"
     * >ISO 3166-1 numeric</a> code.
     *
     * @return The <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric"
     * >ISO 3166-1 numeric</a> code.
     */
    public Integer getId() {
        return id;
    }


    /**
     * Get a CountryCode that corresponds to a given ISO 3166-1
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">alpha-2</a> or
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">alpha-3</a> code.
     *
     * @param code An ISO 3166-1 <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2"
     *             >alpha-2</a> or <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3"
     *             >alpha-3</a> code.
     * @return A CountryCode instance, or null if not found.
     */
    public static CountryCode getByCode(String code) {
        if (code == null) {
            return null;
        }

        switch (code.length()) {
            case 2:
                return getByAlpha2Code(code);

            case 3:
                return getByAlpha3Code(code);

            default:
                return null;
        }
    }

    private static CountryCode getByAlpha2Code(String code) {
        try {
            return Enum.valueOf(CountryCode.class, code);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static CountryCode getByAlpha3Code(String code) {
        return alpha3Map.get(code);
    }


    /**
     * Get a CountryCode that corresponds to a given
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric">ISO 3166-1
     * numeric</a> code.
     *
     * @param code An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric"
     *             >ISO 3166-1 numeric</a> code.
     * @return A CountryCode instance, or null if not found.
     */
    public static CountryCode getByCode(int code) {
        return numericMap.get(code);
    }

    @SuppressWarnings("unused")
    @Converter(autoApply = true)
    public static class EnumAttributeConverter extends BaseEnumConstAttributeConverter<CountryCode, Integer> {
    }
}
