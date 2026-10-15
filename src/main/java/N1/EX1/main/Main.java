package N1.EX1.main;

import N1.EX1.models.Provider;
import N1.EX1.repository.ProviderRepository;

public class Main {
    static void main(String[] args) {

        Provider test = new Provider(
                "Gafas Express S.L.",
                "Gran Via",
                "45",
                "2",
                "1A",
                "Barcelona",
                "08015",
                "España",
                "932223344",
                "932223345",
                "B12345678"
        );

        ProviderRepository providerRepository = new ProviderRepository();
        providerRepository.save(test);
    }
}
