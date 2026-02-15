package com.github.lwichelmann.c3po.run;

import com.github.lwichelmann.c3po.C3poFileType;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.LazyRunConfigurationProducer;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class C3poRunConfigurationProducer extends LazyRunConfigurationProducer<C3poRunConfiguration> {

    @NotNull
    @Override
    public ConfigurationFactory getConfigurationFactory() {
        return ConfigurationTypeUtil.findConfigurationType(C3poRunConfigurationType.class)
                .getConfigurationFactories()[0];
    }

    @Override
    protected boolean setupConfigurationFromContext(@NotNull C3poRunConfiguration configuration,
                                                    @NotNull ConfigurationContext context,
                                                    @NotNull Ref<PsiElement> sourceElement) {
        PsiFile psiFile = getC3poFile(context);
        if (psiFile == null) return false;

        VirtualFile file = psiFile.getVirtualFile();
        configuration.setScriptPath(file.getPath());
        configuration.setName(file.getName());
        return true;
    }

    @Override
    public boolean isConfigurationFromContext(@NotNull C3poRunConfiguration configuration,
                                              @NotNull ConfigurationContext context) {
        PsiFile psiFile = getC3poFile(context);
        if (psiFile == null) return false;

        String scriptPath = configuration.getScriptPath();
        return scriptPath != null && scriptPath.equals(psiFile.getVirtualFile().getPath());
    }

    private static PsiFile getC3poFile(@NotNull ConfigurationContext context) {
        PsiElement location = context.getPsiLocation();
        if (location == null) return null;

        PsiFile psiFile = location.getContainingFile();
        if (psiFile == null) return null;

        if (psiFile.getFileType() != C3poFileType.INSTANCE) return null;

        return psiFile;
    }
}
