package com.misaka.config;

import com.misaka.service.MisakaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The type Project start.
 *
 * @author xiamo
 */
@Slf4j
@Component
public class MisakaStart implements CommandLineRunner {


    @Autowired
    private MisakaService misakaService;

    @Override
    public void run(String... args) throws Exception {
        misakaService.serverInit();
    }
}
