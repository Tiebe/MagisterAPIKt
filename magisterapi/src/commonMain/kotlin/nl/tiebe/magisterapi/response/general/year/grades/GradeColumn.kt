package nl.tiebe.magisterapi.response.general.year.grades

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class GradeColumn (
    @SerialName("Id")
    var id: Int,

    @SerialName("KolomNaam")
    var name: String?,

    @SerialName("KolomNummer")
    var number: String?,

    @SerialName("KolomVolgNummer")
    var index: String,

    @SerialName("KolomKop")
    var heading: String?,

    @SerialName("KolomOmschrijving")
    var description: String?,

    @SerialName("KolomSoort")
    var type: Type,

    @SerialName("IsHerkansingKolom")
    var isCatchUpColumn: Boolean,

    @SerialName("IsDocentKolom")
    var isTeacherColumn: Boolean,

    @SerialName("HeeftOnderliggendeKolommen")
    var HasSubColumns: Boolean,

    @SerialName("IsPTAKolom")
    var isPTAColumn: Boolean,

) {
    @Serializable(with = TypeSerializer::class)
    enum class Type(val type: Int) {
        Unknown(0),
        Grade(1),
        Average(2),
        Maximum(3),
        Formula(4),
        Minimum(5),
        Sum(6),
        Count(7),
        CEVO(8),
        Text(9), // Vrije tekst
        CEVO_CPE(10),
        CEVO_CIE(11),
        Weight(12), // weegfactor
        End(13), // eindcijfer / gemiddelde
        Deficit(14), // tekortpunten
        TreeTop(15), // ???
        SubjectRequirement(16); // vak voorwaarde

        companion object {
            private val map = values().associateBy(Type::type)
            fun fromInt(type: Int) = map[type]
        }
    }

    object TypeSerializer : KSerializer<Type> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("type", PrimitiveKind.INT)

        override fun serialize(encoder: Encoder, value: Type) {
            encoder.encodeInt(value.type)
        }

        override fun deserialize(decoder: Decoder): Type {
            return Type.fromInt(decoder.decodeInt()) ?: Type.Unknown
        }
    }
}