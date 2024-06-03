package Lab.model.DTO.importDTO;

public record AddCompanyDTO(
        String name,
        String town,
        String description // Add this line
) {
    public static AddCompanyDTO empty() {
        return new AddCompanyDTO(null, null, null); // Update this line
    }
}
