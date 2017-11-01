package com.example.modular.modules.types;

import com.example.modular.modules.Module;
import com.example.modular.modules.configuration.ModuleConfiguration;
import com.example.modular.modules.datamodel.Message;

public interface TransformationModule <T> extends Module<T, ModuleConfiguration<T>> {

}
